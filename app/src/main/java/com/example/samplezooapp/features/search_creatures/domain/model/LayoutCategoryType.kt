package com.example.samplezooapp.features.search_creatures.domain.model

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase

enum class LayoutCategoryType {
    ARTHROPODA,
    CANIS_LUPUS,
    DEFAULT
}

fun String.toCategoryType(): LayoutCategoryType
{
   return  when(this.toLowerCase(Locale.current)){

        "arthropoda"->{
            LayoutCategoryType.ARTHROPODA
        }
        "canis lupus"->{
            LayoutCategoryType.CANIS_LUPUS
        }
        else ->{
            LayoutCategoryType.DEFAULT
        }
    }
}