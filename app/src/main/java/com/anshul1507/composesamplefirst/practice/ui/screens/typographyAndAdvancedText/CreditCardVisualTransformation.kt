package com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CreditCardVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        // Transform input string: 12345678901234 -> 1234-5678-9012-1234
        val trimmed = if (text.text.length >= 16) text.text.substring(0, 16) else text.text
        var out = ""

        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i%4 == 3 && i != 15) out += "-"
        }

        //Map cursor indices between original and transformed text layouts
        val creditCardOffsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset + 1
                if (offset <= 11) return offset + 2
                if (offset <= 16) return offset + 3
                return 19
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 9) return offset - 1
                if (offset <= 14) return offset - 2
                if (offset <= 19) return offset - 3
                return 16
            }
        }

        return TransformedText(AnnotatedString(out), creditCardOffsetMapping)
    }
}