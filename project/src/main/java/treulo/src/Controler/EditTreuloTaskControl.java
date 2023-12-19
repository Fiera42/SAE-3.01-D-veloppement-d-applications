package treulo.src.Controler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import treulo.src.model.Model;
import treulo.src.model.TreuloTask;

public class EditTreuloTaskControl implements EventHandler<KeyEvent> {
    Model model;
    TreuloTask TrTa;

    TextField name ;
    TextArea des ;

    public EditTreuloTaskControl(Model m, TreuloTask tt , TextField n , TextArea d)
    {
        model=m;
        TrTa =tt;
        this.name = n ;
        this.des=d  ;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER)
        {
            this.TrTa.setName(this.name.getText());
            this.TrTa.setDescription(this.des.getText());
        }
    }
}
