package br.edu.ifsp.dmo.exemplosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /*
    Definição de constantes para armazenamento de dados em sharedprerences. As constantes
    abaixo indicam o nome do arquivo que armazenará os dados e a outra constante é uma
    chame que indica o campo que será armazenado. No arquivo é possível armazenar diversas
    chaves de diferentes tipos de dados.
     */
    public static final String PREFS_FILE_NAME = "file_name_prefs";
    public static final String KEY_USER = "attr_name";

    private TextView mensagemTextView;
    private EditText nomeEditText;
    private Button salvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensagemTextView = findViewById(R.id.textview_mensagem);
        nomeEditText = findViewById(R.id.edittext_nome);
        salvarButton = findViewById(R.id.button_salvar);
        salvarButton.setOnClickListener(v -> salvarNome());

        updateUI();
    }

    /**
     * Método verifica se foi digitado alguma informação no EditText, havendo algum dado
     * esse é salvo no arquivo de preferencias e a interface do usuário é atualizada.
     */
    private void salvarNome(){
        String nome = nomeEditText.getText().toString();
        if(!nome.isEmpty()){
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE).edit();
            editor.putString(KEY_USER, nome);
            editor.apply();

            updateUI();
        }
    }

    /**
     * Método recupera a informação armazenada no arquivo de preferencias. Caso não
     * exista a preferencia salva retorna-se uma string vazia.
     * @return
     */
    private String recuperaSharedPreferences(){
        SharedPreferences preferences = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);

        /*
        O método getString() da classe SharedProferences retorna a string cuja chave foi indicada
        e o valor padrão caso não tenha encontrado, no código abeixo o padrão é retornar uma
        string vazia.
         */
        return preferences.getString(KEY_USER, "");
    }

    /**
     * Método atualiza a interface do usuário, alterando a mensagem de boas vindas e o texto
     * do botão.
     */
    private void updateUI(){
        boolean update = false;
        String str = recuperaSharedPreferences();
        if(str.isEmpty()){
            str = "Bem-vindo usuário!";
        }else{
            str = "Bem-vindo de volta " + str;
            update = true;
        }
        mensagemTextView.setText(str);
        if (update){
            salvarButton.setText("alterar");
        }
    }
}