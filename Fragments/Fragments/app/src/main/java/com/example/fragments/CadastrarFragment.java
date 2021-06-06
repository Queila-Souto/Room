package com.example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CadastrarFragment extends Fragment {

    private EditText mEditTextPergunta, getmEditTextResposta;

    public CadastrarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastrar, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //listner para o botão jogar
        Button mButtonJogar=getActivity().findViewById(R.id.btnjogar);
        mButtonJogar.setOnClickListener(mJogarListener);

        //Recupera o texto do EditText
        mEditTextPergunta = getActivity().findViewById(R.id.edittextPergunta);
        getmEditTextResposta = getActivity().findViewById(R.id.edittextResposta);

        //Adiciona o Listner para o botão Cadastrar
        Button mButtonInserir = getActivity().findViewById(R.id.btncadastrar);
        mButtonInserir.setOnClickListener(mInserirListener);
    }

    private View.OnClickListener mJogarListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new JogarFragment()).commit();
        }
    };

    private View.OnClickListener mInserirListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //conversao dos valores digitados para string
        String pergunta = mEditTextPergunta.getText().toString();
        String resposta = getmEditTextResposta.getText().toString();

        //verifica se está vazio
        if ((!pergunta.isEmpty()) && ((!resposta.isEmpty()))) {
            //cria um objeto do tipo Questoes com os valores digitados pelo usuario
            Questoes questoes = new Questoes(pergunta,resposta);

            //Insere os dados no banco através da classe DAO
            Database.getBancodedados(getActivity()).getDAO().inserirQuestao(questoes);

            //Limpa os EditText após salvamento
            mEditTextPergunta.setText("");
            getmEditTextResposta.setText("");

            //Exibe mensagem para o usuário
                Toast.makeText(getActivity(), "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
            }

        }
    };

 }