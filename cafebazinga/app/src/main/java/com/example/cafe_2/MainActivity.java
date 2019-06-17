package com.example.cafe_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

 // Variaveis -----------------------------------------------------

    double cafe = 4.0;
    double cafe_leite = 5.0;
    double caputino = 6.0;

    double escolha_feita = 0.0;

//----------------------------->
    double total = 0.0;
    double quant_double = 0.0;

 //---------------------------->
    EditText quant;

    TextView exibindo;
    TextView exibindo2;
    TextView exibindo3;
    TextView exibindo4;

 //- Buttons  para onClick Listener
    public Button btn_precoUni;
    public Button btn_precoTotal;
    public Button btn_pedido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //---------------------------------------------------------------------------->

    //Transformando a o EditText <quant> em Double
        EditText quant = findViewById(R.id.quant);
        quant_double = Double.valueOf(quant.getText().toString());



        //Chamando a funcao <mostraUnitario> pelo BUTTON
        btn_precoUni = findViewById(R.id.btn_precoUni);
        btn_precoUni.setOnClickListener(mostraUnitario);

        //Chamando a funcao <mostraTOTAL> pelo BUTTON
        btn_precoTotal = findViewById(R.id.btn_precoTotal);
        btn_precoTotal.setOnClickListener(mostraTotal);

        //Chamando a funcao pelo BUTTON de PEDIDO
        btn_pedido = findViewById(R.id.btn_pedido);
        btn_pedido.setOnClickListener(mostraPedidoQuant);
        btn_pedido.setOnClickListener(mostraPedidoTotal);

    //BUTTON Fazer Pedido
        Button btn_fazerPedido = findViewById(R.id.fazer_pedido);

        btn_fazerPedido.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent sendEmail = new Intent(Intent.ACTION_SEND);


                sendEmail.setType("text/plain");


                String[] addresses = {"cafe.do.ifc.concordia@gmail.com"};
                sendEmail.putExtra(Intent.EXTRA_EMAIL, addresses);


                sendEmail.putExtra(Intent.EXTRA_TEXT, "Olá, quero um café!");


                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Café!");

                if (sendEmail.resolveActivity(getPackageManager()) != null) {

                    // Inicia a intent
                    startActivity(sendEmail);

                    Log.i("E-mail", "Enviei o intent!");
                }
                Log.i("E-mail", "Botão pressionado!");

            }
        });


    }


    //---------   onclick massa --------------------------------------------------->

    public double cafeOnClick(View view){
        escolha_feita = cafe;
            return escolha_feita;
    }


    public double cafeLeiteOnClick(View view){
        escolha_feita = cafe_leite;
            return escolha_feita;
    }

    public double cafeCaputinoOnClick(View view){
        escolha_feita = caputino;
            return escolha_feita;
    }
    //----------------------------------------------------------------------->

    //Clicando no BUTTON do Preco Unitario
    View.OnClickListener mostraUnitario = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Pega o id do TextView e "seta" nele a escolha feita pela funcao <contaUnitario()>
            exibindo = findViewById(R.id.preco_uni);
            exibindo.setText((int) contaUnitario());
        }
    };
    //Clicando no BUTTON do Preco TOTAL
    View.OnClickListener mostraTotal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Pega o id do TextView e "seta" nele a escolha feita pela funcao <contaTotal()>
            exibindo2 = findViewById(R.id.preco_total);
            exibindo2.setText((int) contaTotal());
        }
    };

    //Clicando no BUTTON do PEDIDO
    View.OnClickListener mostraPedidoQuant = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Pega o id do TextView e "seta" nele a quant
            exibindo3 = findViewById(R.id.text_quant);
            exibindo3.setText((CharSequence) quant);
        }
    };
    View.OnClickListener mostraPedidoTotal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Pega o id do TextView e "seta" nele o total
            exibindo4 = findViewById(R.id.text_total);
            exibindo4.setText((int) contaTotal());
        }
    };

    //---------------------   Calculando os PREÇOS  -------------------------------------------------->
    public double contaTotal(){
        total = quant_double * escolha_feita;

       return total;
    }
    public double contaUnitario(){
        return escolha_feita;
    }



}
