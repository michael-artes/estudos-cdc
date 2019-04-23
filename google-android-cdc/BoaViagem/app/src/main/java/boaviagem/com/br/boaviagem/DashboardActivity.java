package boaviagem.com.br.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mmoreira on 30/09/15.
 */
public class DashboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dashboard);

    }

    public void selecionarOpcao(View v){

        TextView textView = (TextView) v;
        String opcao = "Opção: " + textView.getText().toString();
        Toast.makeText(this, opcao, Toast.LENGTH_LONG).show();

        switch (v.getId()){

            case R.id.nova_viagem:
                startActivity(new Intent(this, ViagemActivity.class));
                break;
        }
    }
}
