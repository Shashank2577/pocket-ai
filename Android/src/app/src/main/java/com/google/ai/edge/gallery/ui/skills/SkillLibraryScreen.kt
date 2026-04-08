/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.ai.edge.gallery.ui.skills

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.res.Configuration
import com.google.ai.edge.gallery.ui.theme.GalleryTheme
import com.google.ai.edge.gallery.ui.theme.clayButton
import com.google.ai.edge.gallery.ui.theme.clayCanvas
import com.google.ai.edge.gallery.ui.theme.clayCanvasDark
import com.google.ai.edge.gallery.ui.theme.clayTextMuted
import com.google.ai.edge.gallery.ui.theme.clayTextMutedDark
import com.google.ai.edge.gallery.ui.theme.clayTextPrimary
import com.google.ai.edge.gallery.ui.theme.clayTextPrimaryDark
import com.google.ai.edge.gallery.ui.theme.clayPressEffect
import com.google.ai.edge.gallery.ui.theme.clayPrimary
import com.google.ai.edge.gallery.ui.theme.clayPrimaryDark
import com.google.ai.edge.gallery.ui.theme.clayPrimaryLighter
import com.google.ai.edge.gallery.ui.theme.clayPrimaryLighterDark
import com.google.ai.edge.gallery.ui.theme.claySecondary
import com.google.ai.edge.gallery.ui.theme.claySecondaryDark
import com.google.ai.edge.gallery.ui.theme.clayShadow
import com.google.ai.edge.gallery.ui.theme.clayTertiary
import com.google.ai.edge.gallery.ui.theme.clayTertiaryDark

data class SkillCardData(
  val title: String,
  val description: String,
  val category: String,
  val skillId: String
)

private val featuredSkillsData = listOf(
  SkillCardData(
    title = "Mood Music",
    description = "Generate music playlists based on your current mood.",
    category = "Featured",
    skillId = "mood-music"
  ),
  SkillCardData(
    title = "Restaurant Roulette",
    description = "Discover random restaurants nearby for your next meal.",
    category = "Featured",
    skillId = "restaurant-roulette"
  )
)

private val builtInSkillsData = listOf(
  SkillCardData(
    title = "Query Wikipedia",
    description = "Query summary from Wikipedia for a given topic.",
    category = "Built-in",
    skillId = "query-wikipedia"
  ),
  SkillCardData(
    title = "Interactive Map",
    description = "Show an interactive map view for the given location.",
    category = "Built-in",
    skillId = "interactive-map"
  ),
  SkillCardData(
    title = "Mood Tracker",
    description = "Track your daily mood and visualize emotional trends.",
    category = "Built-in",
    skillId = "mood-tracker"
  ),
  SkillCardData(
    title = "QR Code",
    description = "Generate QR codes from text or URLs.",
    category = "Built-in",
    skillId = "qr-code"
  ),
  SkillCardData(
    title = "Calculate Hash",
    description = "Calculate hash values for text inputs.",
    category = "Built-in",
    skillId = "calculate-hash"
  ),
  SkillCardData(
    title = "Text Spinner",
    description = "Rewrite and paraphrase text in different styles.",
    category = "Built-in",
    skillId = "text-spinner"
  ),
  SkillCardData(
    title = "Kitchen Adventure",
    description = "Get creative recipe ideas from ingredients you have.",
    category = "Built-in",
    skillId = "kitchen-adventure"
  ),
  SkillCardData(
    title = "Send Email",
    description = "Compose and send emails using natural language.",
    category = "Built-in",
    skillId = "send-email"
  )
)

@Composable
fun SkillLibraryScreen(
  onNavigateToSkills: () -> Unit = {},
  onSkillCardClicked: (String) -> Unit = {}
) {
  val isDark = isSystemInDarkTheme()
  val background = MaterialTheme.colorScheme.background
  val textPrimary = MaterialTheme.colorScheme.onBackground
  val textMuted = MaterialTheme.colorScheme.onSurfaceVariant
  val primary = if (isDark) clayPrimaryDark else clayPrimary
  val primaryLighter = if (isDark) clayPrimaryLighterDark else clayPrimaryLighter
  val featuredOrbColor = if (isDark) claySecondaryDark else claySecondary
  val builtInOrbColor = if (isDark) clayTertiaryDark else clayTertiary

  var searchQuery by remember { mutableStateOf("") }
  val filteredFeatured = featuredSkillsData.filter {
    searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true) ||
      it.description.contains(searchQuery, ignoreCase = true)
  }
  val filteredBuiltIn = builtInSkillsData.filter {
    searchQuery.isEmpty() || it.title.contains(searchQuery, ignoreCase = true) ||
      it.description.contains(searchQuery, ignoreCase = true)
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(background)
      .statusBarsPadding(),
    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp)
  ) {
    // Navbar
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "AI Edge Gallery",
          fontSize = 24.sp,
          fontWeight = FontWeight.Black,
          color = textPrimary
        )

        Box(
          modifier = Modifier
            .size(44.dp)
            .clayShadow(borderRadius = 12.dp, elevation = 4.dp)
            .background(background, RoundedCornerShape(12.dp)),
          contentAlignment = Alignment.Center
        ) {
          // Menu Icon Placeholder
          Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(modifier = Modifier.size(20.dp, 2.dp).background(textPrimary))
            Box(modifier = Modifier.size(20.dp, 2.dp).background(textPrimary))
          }
        }
      }
      Spacer(modifier = Modifier.height(48.dp))
    }

    // Hero Section
    item {
      Text(
        text = "Empower your agents",
        fontSize = 44.sp,
        fontWeight = FontWeight.Black,
        lineHeight = 48.sp,
        color = textPrimary
      )
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = "Ready-to-use skills for the Gemini CLI.",
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = textMuted
      )
      Spacer(modifier = Modifier.height(40.dp))

      // Primary CTA
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(64.dp)
          .clayPressEffect()
          .clayButton(borderRadius = 24.dp, elevation = 8.dp)
          .background(
            brush = Brush.linearGradient(
              colors = listOf(primaryLighter, primary)
            ),
            shape = RoundedCornerShape(24.dp)
          )
          .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onNavigateToSkills
          ),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "Get Started",
          fontSize = 20.sp,
          fontWeight = FontWeight.ExtraBold,
          color = Color.White
        )
      }
      Spacer(modifier = Modifier.height(48.dp))
    }

    // Search bar
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clayShadow(borderRadius = 20.dp, elevation = 4.dp)
          .background(background, RoundedCornerShape(20.dp))
      ) {
        TextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = {
            Text(
              "Search skills...",
              color = textMuted,
              fontWeight = FontWeight.Medium
            )
          },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true,
          colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = primary,
            focusedTextColor = textPrimary,
            unfocusedTextColor = textPrimary,
          ),
          leadingIcon = {
            Icon(
              Icons.Filled.Search,
              contentDescription = "Search",
              tint = textMuted
            )
          }
        )
      }
      Spacer(modifier = Modifier.height(32.dp))
    }

    // Featured section
    if (filteredFeatured.isNotEmpty()) {
      item {
        Text(
          text = "Featured",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = textPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
      }

      items(filteredFeatured) { skill ->
        SkillCard(
          title = skill.title,
          description = skill.description,
          orbColor = featuredOrbColor,
          category = skill.category,
          background = background,
          textPrimary = textPrimary,
          textMuted = textMuted,
          onClick = { onSkillCardClicked(skill.skillId) }
        )
        Spacer(modifier = Modifier.height(24.dp))
      }
    }

    // Built-in section
    if (filteredBuiltIn.isNotEmpty()) {
      item {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "Built-in",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = textPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
      }

      items(filteredBuiltIn) { skill ->
        SkillCard(
          title = skill.title,
          description = skill.description,
          orbColor = builtInOrbColor,
          category = skill.category,
          background = background,
          textPrimary = textPrimary,
          textMuted = textMuted,
          onClick = { onSkillCardClicked(skill.skillId) }
        )
        Spacer(modifier = Modifier.height(24.dp))
      }
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
fun SkillCard(
  title: String,
  description: String = "",
  orbColor: Color,
  category: String = "",
  background: Color,
  textPrimary: Color,
  textMuted: Color,
  onClick: () -> Unit = {}
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(220.dp)
      .clayPressEffect()
      .clayShadow(borderRadius = 32.dp, elevation = 12.dp)
      .background(background, RoundedCornerShape(32.dp))
      .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
      )
      .padding(24.dp)
  ) {
    Column {
      Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Black,
        color = textPrimary
      )

      if (description.isNotEmpty()) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = description,
          fontSize = 16.sp,
          fontWeight = FontWeight.Medium,
          color = textMuted,
          lineHeight = 22.sp
        )
      }
    }

    // Category badge
    if (category.isNotEmpty()) {
      Box(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .background(
            color = orbColor.copy(alpha = 0.15f),
            shape = RoundedCornerShape(12.dp)
          )
          .padding(horizontal = 10.dp, vertical = 4.dp)
      ) {
        Text(
          text = category,
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold,
          color = orbColor
        )
      }
    }

    // Floating Orb
    Box(
      modifier = Modifier
        .size(60.dp)
        .align(Alignment.BottomEnd)
        .clayShadow(borderRadius = 30.dp, elevation = 8.dp, shadowColor = orbColor.copy(alpha = 0.3f))
        .background(
          brush = Brush.linearGradient(
            colors = listOf(orbColor.copy(alpha = 0.6f), orbColor)
          ),
          shape = CircleShape
        )
    )
  }
}

@Preview(showBackground = true, name = "Skill Library - Light")
@Composable
private fun SkillLibraryScreenPreviewLight() {
  GalleryTheme {
    SkillLibraryScreen()
  }
}

@Preview(showBackground = true, name = "Skill Library - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SkillLibraryScreenPreviewDark() {
  GalleryTheme {
    SkillLibraryScreen()
  }
}

@Preview(showBackground = true, name = "Skill Card - Light")
@Composable
private fun SkillCardPreviewLight() {
  GalleryTheme {
    SkillCard(
      title = "Query Wikipedia",
      description = "Query summary from Wikipedia for a given topic.",
      orbColor = clayTertiary,
      category = "Built-in",
      background = clayCanvas,
      textPrimary = clayTextPrimary,
      textMuted = clayTextMuted
    )
  }
}

@Preview(showBackground = true, name = "Skill Card - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SkillCardPreviewDark() {
  GalleryTheme {
    SkillCard(
      title = "Mood Music",
      description = "Generate music playlists based on your current mood.",
      orbColor = claySecondaryDark,
      category = "Featured",
      background = clayCanvasDark,
      textPrimary = clayTextPrimaryDark,
      textMuted = clayTextMutedDark
    )
  }
}
