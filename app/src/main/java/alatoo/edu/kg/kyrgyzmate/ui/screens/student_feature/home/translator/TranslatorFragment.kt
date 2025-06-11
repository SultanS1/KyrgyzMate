package alatoo.edu.kg.kyrgyzmate.ui.screens.student_feature.home.translator

import alatoo.edu.kg.kyrgyzmate.R
import alatoo.edu.kg.kyrgyzmate.databinding.FragmentStudentTranslateBinding
import alatoo.edu.kg.kyrgyzmate.extensions.pressCompressInAnimation
import alatoo.edu.kg.kyrgyzmate.extensions.setClickListener
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslatorFragment : Fragment(R.layout.fragment_student_translate) {

    private val binding by viewBinding(FragmentStudentTranslateBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            openCameraButton.setClickListener {
                Toast.makeText(requireContext(), "Soon", Toast.LENGTH_SHORT).show()
            }

            translateActionButton.pressCompressInAnimation()
            translateActionButton.setClickListener {
                translateText(
                    text = binding.contentTextEditText.text.toString(),
                    onSuccess = { translatedText ->
                        contentTextEditText.setText(translatedText)
                    },
                    onError = { error ->
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }

    private fun translateText(text: String, onSuccess: (String) -> Unit, onError: (Exception) -> Unit) {
        if (TranslateLanguage.fromLanguageTag("ru") == null) {
            onError(Exception("Russian translation not supported on this device"))
            return
        }

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.RUSSIAN)
            .build()

        val translator = Translation.getClient(options)

        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()

        translator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                translator.translate(text)
                    .addOnSuccessListener { translatedText ->
                        onSuccess(translatedText)
                        translator.close()
                    }
                    .addOnFailureListener { e ->
                        onError(Exception("Translation failed: ${e.message}"))
                        translator.close()
                    }
            }
            .addOnFailureListener { e ->
                onError(Exception("Model download failed: ${e.message}"))
                translator.close()
            }
    }
}