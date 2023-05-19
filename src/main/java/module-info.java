module v.visual {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires javafx.swing;
    requires org.bytedeco.javacv;
    requires org.bytedeco.ffmpeg;

    opens v.visual to javafx.fxml;
    exports v.visual;
}