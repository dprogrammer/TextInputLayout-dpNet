package net.dprogrammer.textinputlayout_dpnet;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

    /*

    Exemplo criando em 10/12/2015 por Paulo Miranda
    Site: http://dprogrammer.net

    O objetivo desse exemplo é mostrar o funcionamento do componente TextInputLayout
    que foi introduzido na Android Design Support Library

    Você também vai aprender a validar um endereço de email de uma forma bem simples.

    */

public class LoginActivity extends AppCompatActivity {

    TextInputLayout inputLayoutEmail, inputLayoutPassword;
    EditText edEmail, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);

        edEmail = (EditText) findViewById(R.id.email);
        edPassword = (EditText) findViewById(R.id.password);

        edPassword.addTextChangedListener(new MyTextWatcher(edPassword));
        edEmail.addTextChangedListener(new MyTextWatcher(edEmail));
    }

    // método do botão btAcessar declarado no layout, na propriedade onClick
    public void Acessar(View v){

        if (!validarEmail()) {
            return;
        }

        if (!validarPassword()) {
            return;
        }

        Toast.makeText(this, "Login válido!!!", Toast.LENGTH_LONG).show();

    }

    // validar a senha
    private boolean validarPassword() {
        if (edPassword.getText().toString().trim().length() < 6) {
            inputLayoutPassword.setError(getString(R.string.erro_msg_password));
            requestFocus(edPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    // validar o endereço de e-mail
    private boolean validarEmail() {
        String email = edEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.erro_msg_email));
            requestFocus(edEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    // verifica se o formato do email é válido
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // setar o foco de um determinado componente
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    // validar os campos no momento da digitação
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.password:
                    validarPassword();
                    break;
                case R.id.email:
                    validarEmail();
                    break;
            }
        }
    }

}
