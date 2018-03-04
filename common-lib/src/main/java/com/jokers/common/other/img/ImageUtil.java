package com.jokers.common.other.img;

import com.jokers.common.message.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yuton
 * 图片缩放截取Util
 * @since May 12, 2011 11:27:55 AM
 */
@Slf4j
public class ImageUtil {

    /**
     * 实现图像的等比缩放
     *
     * @param source  source
     * @param targetW 长
     * @param targetH 宽
     * @return BufferedImage
     */
    public static BufferedImage resize(BufferedImage source, int targetW,
                                       int targetH) {
        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
        if (sx < sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
                    targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else
            target = new BufferedImage(targetW, targetH, type);
        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }

    /**
     * 实现图像的等比缩放和缩放后的截取
     *
     * @param inFilePath  要截取文件的路径
     * @param outFilePath 截取后输出的路径
     * @param width       要截取宽度
     * @param hight       要截取的高度
     * @param proportion  比例
     */
    public static void saveImageAsJpg(String inFilePath, String outFilePath,
                                      int width, int hight, boolean proportion) throws Exception {
        File file = new File(inFilePath);
        InputStream in = new FileInputStream(file);
        File saveFile = new File(outFilePath);

        BufferedImage srcImage = ImageIO.read(in);
        if (width > 0 || hight > 0) {
            // 原图的大小
            int sw = srcImage.getWidth();
            int sh = srcImage.getHeight();
            // 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去
            if (sw > width && sh > hight) {
                srcImage = resize(srcImage, width, hight);
            } else {
                String fileName = saveFile.getName();
                String formatName = fileName.substring(fileName
                        .lastIndexOf('.') + 1);
                ImageIO.write(srcImage, formatName, saveFile);
                return;
            }
        }
        // 缩放后的图像的宽和高
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();
        // 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取
        if (w == width) {
            // 计算X轴坐标
            int x = 0;
            int y = h / 2 - hight / 2;
            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
        }
        // 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取
        else if (h == hight) {
            // 计算X轴坐标
            int x = w / 2 - width / 2;
            int y = 0;
            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
        }
        in.close();
    }

    /**
     * @param is is
     * @param deistPath 路径
     * @param newWidth 新路径
     * @param newHeight 新高度
     * @return int
     *  saveCompress
     * : 压缩图片，统一压缩成JPG格式，返回压缩后的大小（字节）
     */
    public static int saveCompress(InputStream is, String deistPath,
                                   int newWidth, int newHeight) {
        try {
            BufferedImage srcImage = ImageIO.read(is);
            BufferedImage destImage = resize(srcImage, newWidth, newHeight);
            File deistFile = new File(deistPath);
            String fileName = deistFile.getName();
            String formatName = fileName
                    .substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(destImage, formatName, deistFile);
            return (int) deistFile.length();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param is is
     * @param deistPath 路径
     * @param newWidth 新路径
     * @return int
     *  saveCompressBaseWidth
     * : 基于宽度等比压缩
     */
    public static int saveCompressBaseWidth(InputStream is, String deistPath,
                                            int newWidth) {
        return saveCompressBaseHeight(is, deistPath, newWidth);
    }

    /**
     * @param bytes 字节
     * @param deistPath 路径
     * @param newWidth 新路径
     * @return int
     *  saveCompressBaseWidth
     * : 基于宽度等比压缩
     */
    public static int saveCompressBaseWidth(byte[] bytes, String deistPath,
                                            int newWidth) {
        try {
            BufferedImage srcImage = ImageIO.read(new ByteArrayInputStream(
                    bytes));
            return getCompressBaseWidthOrHight(srcImage, deistPath, newWidth);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param is is
     * @param deistPath 路径
     * @param newWidth 新路径
     * @return int
     *  compressBaseHeight
     * : 基于高度等比压缩
     */
    public static int saveCompressBaseHeight(InputStream is, String deistPath,
                                             int newWidth) {
        try {
            BufferedImage srcImage = ImageIO.read(is);
            return getCompressBaseWidthOrHight(srcImage, deistPath, newWidth);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param is is
     * @param deistPath 路径
     * @param newH 新路径
     * @return int
     *  saveCompressAndCutBaseHeight
     * : 压缩并截取图片，以高度为基准压缩，截图为居中正方形
     */
    public static int saveCompressAndCutBaseHeight(InputStream is,
                                                   String deistPath, int newH) {
        try {
            BufferedImage srcImage = ImageIO.read(is);
            return getCompressAndCutBaseHeight(srcImage, deistPath, newH);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param deistPath 路径
     * @param newH 新路径
     * @param data 日期
     * @return int
     *  saveCompressAndCutBaseHeight
     * : 压缩并截取图片，以高度为基准压缩，截图为居中正方形
     */
    public static int saveCompressAndCutBaseHeight(byte[] data,
                                                   String deistPath, int newH) {
        try {
            BufferedImage srcImage = ImageIO
                    .read(new ByteArrayInputStream(data));
            return getCompressAndCutBaseHeight(srcImage, deistPath, newH);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param is is
     * @param deistPath 路径
     * @param newH 新路径
     * @return int
     *  savePicture
     * : 按比例压缩图片
     */
    public static int savePictureTrumbs(InputStream is, String deistPath,
                                        int newH) {
        return saveCompressAndCutBaseHeight(is, deistPath, newH);
    }

    /**
     * @param data 日期
     * @param deistPath 路径
     * @param newH 新路径
     * @return int
     *  savePicture
     * : 按比例压缩图片
     */
    public static int savePictureTrumbs(byte[] data, String deistPath, int newH) {
        return saveCompressAndCutBaseHeight(data, deistPath, newH);
    }

    /**
     * @param is is
     * @param x x
     * @param y y
     * @param width width
     * @param height height
     * @return BufferedImage
     *  cutImage
     * : 裁剪图片
     */
    public static BufferedImage cutImage(InputStream is, int x, int y,
                                         int width, int height) {
        try {
            BufferedImage srcImage = ImageIO.read(is);
            return srcImage.getSubimage(x, y, width, height);
        } catch (Exception e) {
            log.error(ExceptionMessage.IO_EXCEPTION.getExceptionMsg());
            return null;
        }
    }

    /**
     * @param bi bi
     * @param nw nw
     * @param nh nh
     * @param tarFile tarFile
     * @return boolean
     *  compressAndSave
     * : 压缩处理
     */
    public static boolean compressAndSave(BufferedImage bi, int nw, int nh,
                                          File tarFile) {
        try {
            BufferedImage destImage = resize(bi, nw, nh);
            // 输出文件
            String fileName = tarFile.getName();
            String formatName = fileName
                    .substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(destImage, formatName, tarFile);
            return true;
        } catch (Exception e) {
            log.error(ExceptionMessage.IO_EXCEPTION.getExceptionMsg());
            return false;
        }
    }

    /**
     * 实现缩放后的截图
     *
     * @param image          缩放后的图像
     * @param subImageBounds 要截取的子图的范围
     * @param subImageFile   要保存的文件
     *
     */
    private static void saveSubImage(BufferedImage image,
                                     Rectangle subImageBounds, File subImageFile) throws IOException {
        if (subImageBounds.x < 0 || subImageBounds.y < 0
                || subImageBounds.width - subImageBounds.x > image.getWidth()
                || subImageBounds.height - subImageBounds.y > image.getHeight()) {
            System.out.println("Bad   subimage   bounds");
            return;
        }
        BufferedImage subImage = image.getSubimage(subImageBounds.x,
                subImageBounds.y, subImageBounds.width, subImageBounds.height);
        String fileName = subImageFile.getName();
        String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
        ImageIO.write(subImage, formatName, subImageFile);
    }

//    public static void main(String[] args) {
//        try {
//            int size = saveCompress(new FileInputStream(
//                    new File("d:\\test.jpg")), "d:\\test1.jpg", 200, 150);
//            System.out.println(size);
//            // size = saveCompress(new FileInputStream(new
//            // File("d:\\test.gif")), "d:\\test2.gif", 30, 30);
//            // System.out.println(size);
//            // size = saveCompress(new FileInputStream(new
//            // File("d:\\test.png")), "d:\\test3.png", 30, 30);
//            // System.out.println(size);
//        } catch (Exception e) {
//
//        }
//    }

    private static int getCompressBaseWidthOrHight(BufferedImage srcImage, String destPath, int newWidth) throws IOException {
        int srcW = srcImage.getWidth();
        int srcH = srcImage.getHeight();
        File destFile = new File(destPath);
        String fileName = destFile.getName();
        String formatName = fileName
                .substring(fileName.lastIndexOf('.') + 1);
        if (newWidth < srcW) {
            int newHeight = (srcH * newWidth) / srcW;
            BufferedImage destImage = resize(srcImage, newWidth, newHeight);
            ImageIO.write(destImage, formatName, destFile);
        } else {
            ImageIO.write(srcImage, formatName, destFile);
        }
        return (int) destFile.length();
    }

    private static int getCompressAndCutBaseHeight(BufferedImage srcImage, String destPath, int newH) throws IOException {
        int srcW = srcImage.getWidth();
        int srcH = srcImage.getHeight();
        File destFile = new File(destPath);
        String fileName = destFile.getName();
        String formatName = fileName
                .substring(fileName.lastIndexOf('.') + 1);
        if (newH < srcH) {

            int x = 0;
            int y = 0;
            int newLength = 0;
            if (srcW < srcH) {
                // 竖图
                y = srcH / 2 - (srcW / 2);
                newLength = srcW;
            } else {
                x = srcW / 2 - (srcH / 2);
                newLength = srcH;
            }
            // 截取图片
            srcImage = srcImage.getSubimage(x, y, newLength, newLength);
            BufferedImage destImage = resize(srcImage, newH, newH);
            ImageIO.write(destImage, formatName, destFile);
        } else {
            // 截取图片
            srcImage = srcImage.getSubimage(srcW / 2 - srcH / 2, 0, srcH,
                    srcH);
            ImageIO.write(srcImage, formatName, destFile);
        }
        return (int) destFile.length();
    }
}
