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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.ai.edge.gallery.ui.theme.clayButton
import com.google.ai.edge.gallery.ui.theme.clayCanvas
import com.google.ai.edge.gallery.ui.theme.clayPrimary
import com.google.ai.edge.gallery.ui.theme.clayPrimaryLighter
import com.google.ai.edge.gallery.ui.theme.claySecondary
import com.google.ai.edge.gallery.ui.theme.clayShadow
import com.google.ai.edge.gallery.ui.theme.clayTertiary
import com.google.ai.edge.gallery.ui.theme.clayTextMuted
import com.google.ai.edge.gallery.ui.theme.clayTextPrimary

@Composable
fun SkillLibraryScreen(
  onNavigateToSkills: () -> Unit = {}
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(clayCanvas)
      .statusBarsPadding()
      .verticalScroll(scrollState)
      .padding(horizontal = 24.dp)
  ) {
    Spacer(modifier = Modifier.height(24.dp))
    
    // Navbar
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Gemini",
        fontSize = 24.sp,
        fontWeight = FontWeight.Black,
        color = clayTextPrimary
      )
      
      Box(
        modifier = Modifier
          .size(44.dp)
          .clayShadow(borderRadius = 12.dp, elevation = 4.dp)
          .background(clayCanvas, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
      ) {
        // Menu Icon Placeholder
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Box(modifier = Modifier.size(20.dp, 2.dp).background(clayTextPrimary))
          Box(modifier = Modifier.size(20.dp, 2.dp).background(clayTextPrimary))
        }
      }
    }

    Spacer(modifier = Modifier.height(48.dp))

    // Hero Section
    Text(
      text = "Empower your agents",
      fontSize = 44.sp,
      fontWeight = FontWeight.Black,
      lineHeight = 48.sp,
      color = clayTextPrimary
    )
    
    Spacer(modifier = Modifier.height(16.dp))
    
    Text(
      text = "Ready-to-use skills for the Gemini CLI.",
      fontSize = 18.sp,
      fontWeight = FontWeight.Medium,
      color = clayTextMuted
    )

    Spacer(modifier = Modifier.height(40.dp))

    // Primary CTA
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(64.dp)
        .clayButton(borderRadius = 24.dp, elevation = 8.dp)
        .background(
          brush = Brush.linearGradient(
            colors = listOf(clayPrimaryLighter, clayPrimary)
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

    // Skill Bento Cards
    SkillCard(
      title = "Mood Music",
      description = "Generate music playlists based on your current mood.",
      orbColor = claySecondary
    )
    
    Spacer(modifier = Modifier.height(24.dp))
    
    SkillCard(
      title = "Virtual Piano",
      description = "Play and record piano music with your keyboard.",
      orbColor = clayTertiary
    )

    Spacer(modifier = Modifier.height(48.dp))
  }
}

@Composable
fun SkillCard(
  title: String,
  description: String = "",
  orbColor: Color
) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(220.dp)
      .clayShadow(borderRadius = 32.dp, elevation = 12.dp)
      .background(clayCanvas, RoundedCornerShape(32.dp))
      .padding(24.dp)
  ) {
    Column {
      Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.Black,
        color = clayTextPrimary
      )
      
      if (description.isNotEmpty()) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = description,
          fontSize = 16.sp,
          fontWeight = FontWeight.Medium,
          color = clayTextMuted,
          lineHeight = 22.sp
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
