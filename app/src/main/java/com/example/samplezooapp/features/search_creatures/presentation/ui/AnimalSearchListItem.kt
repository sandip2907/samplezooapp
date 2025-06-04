package com.example.samplezooapp.features.search_creatures.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.samplezooapp.R
import com.example.samplezooapp.features.search_creatures.domain.model.Animal
import com.example.samplezooapp.features.search_creatures.domain.model.LayoutCategoryType
import com.example.samplezooapp.features.search_creatures.domain.model.toCategoryType

@Composable
fun AnimalSearchListItem(
    modifier: Modifier,
    animal: Animal
) {
    Card(modifier = modifier.padding(dimensionResource(R.dimen.item_card_padding))) {
        Surface(
            tonalElevation = 4.dp
        ) {
                Column (modifier = Modifier.padding(10.dp)){
                    animal.name?.let {
                        Text(
                            text = stringResource(R.string.name, it),
                            maxLines = 1,
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                    animal.taxonomy.phylum?.let {
                        Text(
                            text = stringResource(R.string.phylum, animal.taxonomy.phylum),
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(2.dp)
                        )


                            if (it.toCategoryType() == LayoutCategoryType.ARTHROPODA) {

                                    animal.characteristics.prey?.let {
                                        Text(
                                            text = stringResource(R.string.prey, it),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            fontStyle = FontStyle.Italic,
                                            textAlign = TextAlign.End,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(2.dp)
                                        )
                                    }

                                    animal.characteristics.predators?.let {
                                        Text(
                                            text = stringResource(R.string.predators, it),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            fontStyle = FontStyle.Italic,
                                            textAlign = TextAlign.End,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    animal.taxonomy.scientificName?.let {
                        Text(
                            text = stringResource(R.string.scientific_name, it),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(2.dp)
                        )
                        if (it.toCategoryType() == LayoutCategoryType.CANIS_LUPUS) {
                            animal.characteristics.slogan?.let {
                                Text(
                                    text = stringResource(R.string.slogan, it),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(2.dp)
                                )
                            }

                            animal.characteristics.lifespan?.let {
                                Text(
                                    text = stringResource(R.string.lifespan, it),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.End,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(2.dp)
                                )
                            }
                        }
                    }
        animal.characteristics.wingspan?.let {
            Text(
                text = stringResource(R.string.wingspan, it),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(2.dp)
            )

            animal.characteristics.habitat?.let {
                Text(
                    text = stringResource(R.string.habitat, it),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}
