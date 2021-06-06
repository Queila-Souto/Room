package com.example.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class JogarFragment extends Fragment {


    private Button btnCadastrar;
    private List<Questoes> mListQuestoes;
    private TextView mTextViewPergunta, getmTextViewResposta;

    public JogarFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jogar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Adiciona Listener para o botão cadastro
        btnCadastrar = getActivity().findViewById(R.id.btncadastro);
        btnCadastrar.setOnClickListener(mCadastrarListener);

        //Adiciona Listener para o botão ExibirResposta
        Button mButtonExibirResposta = getActivity().findViewById(R.id.btnExResposta);
        mButtonExibirResposta.setOnClickListener(mExibirRespostaListener);

        //Adiciona Listener ao botão pular
        Button mButtonPular = getActivity().findViewById(R.id.btnpular);
        mButtonPular.setOnClickListener(mPularListener);

        //Recupera os TextView do layout
        mTextViewPergunta = getActivity().findViewById(R.id.TVExibirPergunta);
        getmTextViewResposta = getActivity().findViewById(R.id.TVExibirResposta);

        //Recupera as questoes do BD
        mListQuestoes = Database.getBancodedados(getActivity()).getDAO().pesquisarTodasQuestoes();

        //Exibir a questão ao iniciar o fragment
        proximaQuestao();
    }

    private View.OnClickListener mCadastrarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //ir para o fragmentCadastrar
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new CadastrarFragment()).commit();
        }
    };

    private  View.OnClickListener mExibirRespostaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //exibir a resposta na tela
            exibirResposta();
        }
    };

    private View.OnClickListener mPularListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //inicia a proxima questão
            proximaQuestao();
        }
    };

    private void proximaQuestao(){
        //verifica se a minha lista de questoes no BD está vazia
        if (!mListQuestoes.isEmpty()){
            //cria uma variavel com o numero total de questoes cadastradas no BD
            int totalDeQuestoes = mListQuestoes.size();

            //cria um numero aleatório dentre o total de questôes cadastradas
            int indexAleatorio = new Random().nextInt(totalDeQuestoes);

            //Recupera um objeto Questoes da lista de forma aleatória
            Questoes questoes = mListQuestoes.get(indexAleatorio);

            //Exibe para o usuario a pergunta e a resposta:
            mTextViewPergunta.setText(questoes.getPergunta());
            getmTextViewResposta.setText(questoes.getResposta());

            //Mantem a resposta oculta
            getmTextViewResposta.setVisibility(View.GONE);

        }
    }

    private void exibirResposta(){
        getmTextViewResposta.setVisibility(View.VISIBLE);
    }

}