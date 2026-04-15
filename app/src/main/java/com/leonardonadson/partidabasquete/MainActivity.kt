package com.leonardonadson.partidabasquete

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.CountDownTimer
import android.app.AlertDialog


class MainActivity : ComponentActivity() {

    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView
    private lateinit var textCronometro: TextView

    private var countDownTimer: CountDownTimer? = null
    private var tempoRestanteInMillis: Long = 600000
    private var timerRodando: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Restaura estado salvo (caso Activity seja recriada pelo sistema)
        if (savedInstanceState != null) {
            pontuacaoTimeA = savedInstanceState.getInt("pontuacaoA", 0)
            pontuacaoTimeB = savedInstanceState.getInt("pontuacaoB", 0)
            tempoRestanteInMillis = savedInstanceState.getLong("tempoRestante", 600000)
            timerRodando = savedInstanceState.getBoolean("timerRodando", false)
        }

        setContentView(R.layout.activity_main)
        setupViews()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Re-infla o layout correto para a nova orientação
        setContentView(R.layout.activity_main)
        setupViews()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("pontuacaoA", pontuacaoTimeA)
        outState.putInt("pontuacaoB", pontuacaoTimeB)
        outState.putLong("tempoRestante", tempoRestanteInMillis)
        outState.putBoolean("timerRodando", timerRodando)
    }

    private fun setupViews() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)
        textCronometro = findViewById(R.id.cronometro)

        // Restaura valores na UI
        pTimeA.text = pontuacaoTimeA.toString()
        pTimeB.text = pontuacaoTimeB.toString()
        atualizarTextoCronometro()

        // Se o timer estava rodando, retoma-o
        if (timerRodando) {
            iniciarTimer()
        }

        val bTresPontosTimeA: Button = findViewById(R.id.tresPontosA)
        val bDoisPontosTimeA: Button = findViewById(R.id.doisPontosA)
        val bTLivreTimeA: Button = findViewById(R.id.tiroLivreA)
        val bTresPontosTimeB: Button = findViewById(R.id.tresPontosB)
        val bDoisPontosTimeB: Button = findViewById(R.id.doisPontosB)
        val bTLivreTimeB: Button = findViewById(R.id.tiroLivreB)
        val bReiniciar: Button = findViewById(R.id.reiniciarPartida)

        bTresPontosTimeA.setOnClickListener { adicionarPontos(3, "A") }
        bDoisPontosTimeA.setOnClickListener { adicionarPontos(2, "A") }
        bTLivreTimeA.setOnClickListener { adicionarPontos(1, "A") }
        bTresPontosTimeB.setOnClickListener { adicionarPontos(3, "B") }
        bDoisPontosTimeB.setOnClickListener { adicionarPontos(2, "B") }
        bTLivreTimeB.setOnClickListener { adicionarPontos(1, "B") }

        bReiniciar.setOnClickListener { reiniciarPartida() }

        textCronometro.setOnClickListener {
            if (timerRodando) {
                pausarTimer()
            } else {
                iniciarTimer()
            }
        }
    }

    private fun adicionarPontos(pontos: Int, time: String) {
        if (time == "A") {
            pontuacaoTimeA += pontos
        } else {
            pontuacaoTimeB += pontos
        }
        atualizaPlacar(time)

        if (pontuacaoTimeA >= 100) {
            mostrarTelaVencedor("TIME A")
        } else if (pontuacaoTimeB >= 100) {
            mostrarTelaVencedor("TIME B")
        }
    }

    private fun atualizaPlacar(time: String) {
        if (time == "A") {
            pTimeA.text = pontuacaoTimeA.toString()
        } else {
            pTimeB.text = pontuacaoTimeB.toString()
        }
    }

    private fun iniciarTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(tempoRestanteInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tempoRestanteInMillis = millisUntilFinished
                atualizarTextoCronometro()
            }

            override fun onFinish() {
                timerRodando = false
                Toast.makeText(this@MainActivity, "Fim de Quarto!", Toast.LENGTH_LONG).show()
            }
        }.start()

        timerRodando = true
    }

    private fun pausarTimer() {
        countDownTimer?.cancel()
        timerRodando = false
    }

    private fun atualizarTextoCronometro() {
        val minutos = (tempoRestanteInMillis / 1000) / 60
        val segundos = (tempoRestanteInMillis / 1000) % 60

        val tempoFormatado = String.format("%02d:%02d", minutos, segundos)
        textCronometro.text = tempoFormatado
    }

    private fun reiniciarPartida() {
        pontuacaoTimeA = 0
        pTimeA.text = pontuacaoTimeA.toString()
        pontuacaoTimeB = 0
        pTimeB.text = pontuacaoTimeB.toString()

        pausarTimer()
        tempoRestanteInMillis = 600000
        atualizarTextoCronometro()

        Toast.makeText(this, "Partida e Cronômetro reiniciados", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarTelaVencedor(nomeDoTime: String) {
        pausarTimer()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("🏆 Fim de Jogo!")
        builder.setMessage("O $nomeDoTime atingiu 100 pontos e venceu a partida!")

        builder.setCancelable(false)

        builder.setPositiveButton("Nova Partida") { dialog, _ ->
            reiniciarPartida()
            dialog.dismiss()
        }

        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}