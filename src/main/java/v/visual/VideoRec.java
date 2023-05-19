package v.visual;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.controlsfx.control.Notifications;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.bytedeco.javacv.FFmpegFrameGrabber;

public class VideoRec{
    private LessonTrainWin lessonTrainWin;

    public VideoRec(LessonTrainWin lessonTrainWin) {
        this.lessonTrainWin = lessonTrainWin;
    }

//    static {
//        try {
//            //Загружаем необходимые библиотеки
//            Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
//            Loader.load(org.bytedeco.ffmpeg.swresample.class);
//            Loader.load(org.bytedeco.ffmpeg.avutil.class);
//            Loader.load(org.bytedeco.ffmpeg.avcodec.class);
//            Loader.load(org.bytedeco.ffmpeg.avformat.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private boolean isRecording = false;
    private File outputFile;
    private Thread recordingThread;

    public void startRecording() {
        if (isRecording) {
            return;
        }
        System.out.println("startRecording");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String fileName = "screen_recording_" + dateFormat.format(new Date()) + ".mp4";
        outputFile = new File(fileName);
        isRecording = true;
        //Запускаем поток записи видео экрана
        recordingThread = new Thread(() -> {
            try {
                double frameRate = 20; //количество кадров в секунду
                Rectangle2D screenBounds = Screen.getPrimary().getBounds();
                int width = (int) screenBounds.getWidth();
                int height = (int) screenBounds.getHeight();

                FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("desktop");
                grabber.setFormat("gdigrab");
                grabber.setImageWidth(width);
                grabber.setImageHeight(height);
                grabber.start();

                FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, width, height);
                recorder.setVideoCodecName("libx264");
                recorder.setAudioCodecName("aac");
                recorder.setFrameRate(frameRate);
                recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
                recorder.start();

                long startTime = System.currentTimeMillis();
                while (isRecording) {
                    Frame frame = grabber.grabImage();
                    if (frame != null) {
                        recorder.record(frame);
                    }

                    //Задержка для соблюдения частоты кадров
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    long expectedSleepTime = (long) (1000 / frameRate);
                    long sleepTime = Math.max(expectedSleepTime - elapsedTime, 0);
                    Thread.sleep(sleepTime);

                    startTime = System.currentTimeMillis();
                }

                recorder.stop();
                recorder.release();

                Platform.runLater(() -> {
                    Notifications.create()
                            .title("Screen Recording")
                            .text("Recorded video saved to " + outputFile.getAbsolutePath())
                            .showInformation();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        recordingThread.start();

        if (recordingThread != null) {
            System.out.println("rt started");
        } else {
            System.out.println("rt null");
        }
    }

    public void stopRecording() {
        System.out.println("0");
        if (recordingThread != null) {
            System.out.println("rt started");
        } else {
            System.out.println("rt null");
        }

        if (!isRecording || recordingThread == null) {
            return;
        }
        isRecording = false;

        try {
            recordingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("stopRecording");
    }
}
