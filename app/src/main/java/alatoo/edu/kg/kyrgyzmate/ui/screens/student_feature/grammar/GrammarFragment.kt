package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.grammar

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentGrammarBinding
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding

class GrammarFragment : Fragment(R.layout.fragment_grammar) {

    private val binding by viewBinding(FragmentGrammarBinding::bind)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: GrammarFragmentArgs by navArgs()
        val id = args.id

        with(binding) {
            arrowBackButton.setClickListener {
                binding.arrowBackButton.setClickListener {
                    val bundle = Bundle().apply {
                        putString("topic_id", "")
                    }
                    parentFragmentManager.setFragmentResult("my_result_key", bundle)

                    findNavController().popBackStack() }
            }

            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(false)
            webView.settings.builtInZoomControls = false
            webView.settings.displayZoomControls = false

            webView.isHorizontalScrollBarEnabled = false
            webView.settings.loadWithOverviewMode = true
            webView.settings.useWideViewPort = false

            webView.webViewClient = WebViewClient()
            webView.settings.userAgentString =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90 Safari/537.36"

            webView.loadUrl("https://docs.google.com/document/d/$id/preview")
        }
    }
}