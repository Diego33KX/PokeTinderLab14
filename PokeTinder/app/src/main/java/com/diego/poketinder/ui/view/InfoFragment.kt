package com.diego.poketinder.ui.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.diego.poketinder.R
import com.diego.poketinder.databinding.InfoFragmentBinding
import com.diego.poketinder.viewmodel.InfoViewModel

class InfoFragment : BaseFragment<InfoFragmentBinding>(InfoFragmentBinding::inflate) {
    private val viewModel: InfoViewModel by viewModels()
    private lateinit var webView: WebView

    //MANTENEMOS EL ESTADO DE LA PÁGINA PARA QUE NO SE RECARGUE
    //CADA QUE CAMBIEMOS DE FRAGMENT
    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = binding.wvMain
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = PokemonWebClient()

        viewModel.getUrlPokemon().observe(viewLifecycleOwner){
            savedInstanceState?.let{
                webView.restoreState(it)
            }?: webView.loadUrl(it)
        }
    }

    inner class PokemonWebClient:WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {

            webView!!.loadUrl("javascript:(function(){" +
                    "document.getElementsByClassName('navbar top')[0].style.display='none';})()")
        }
    }
}