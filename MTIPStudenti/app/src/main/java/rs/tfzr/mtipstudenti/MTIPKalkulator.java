package rs.tfzr.mtipstudenti;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MTIPKalkulator extends AppCompatActivity {

    protected Context _context;

    protected EditText _txtPrviBroj;
    protected EditText _txtDrugiBroj;
    protected Button _btnIzracunaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mtipkalkulator);

        this._context = this;

        this._txtPrviBroj = findViewById(R.id.editTextPrviBroj);
        this._txtDrugiBroj = findViewById(R.id.editTextDrugiBroj);
        this._btnIzracunaj = findViewById(R.id.btnIzracunaj);

        this._btnIzracunaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int prviBroj = Integer.parseInt(_txtPrviBroj.getText().toString());
                int drugiBroj = Integer.parseInt(_txtDrugiBroj.getText().toString());

                Toast.makeText(_context,"Rezultat sabiranja je : " + String.valueOf(prviBroj+drugiBroj),Toast.LENGTH_LONG).show();

            }
        });

    }
}
