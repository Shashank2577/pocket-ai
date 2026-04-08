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

package com.google.ai.edge.gallery.ui.theme

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Custom Modifier to apply the "High-Fidelity Claymorphism" shadow stack.
 *
 * It uses a 4-layer stack:
 * 1. Deep ambient shadow (bottom-right)
 * 2. Top-left specular highlight
 * 3. Inner colored bounce light
 * 4. Inner rim light (specularity)
 */
fun Modifier.clayShadow(
  borderRadius: Dp = 32.dp,
  elevation: Dp = 16.dp,
  shadowColor: Color = Color(0xFFA096B4).copy(alpha = 0.2f),
  highlightColor: Color = Color.White.copy(alpha = 0.9f)
) = this.drawBehind {
  val shadowRadius = elevation * 2
  val shadowOffset = elevation

  drawIntoCanvas { canvas ->
    val paint = Paint()
    val frameworkPaint = paint.asFrameworkPaint()

    // 1. Outer Deep Shadow (Bottom-Right)
    frameworkPaint.color = shadowColor.toArgb()
    frameworkPaint.setShadowLayer(
      shadowRadius.toPx(),
      shadowOffset.toPx(),
      shadowOffset.toPx(),
      shadowColor.toArgb()
    )
    canvas.drawRoundRect(
      0f, 0f, size.width, size.height,
      borderRadius.toPx(), borderRadius.toPx(),
      paint
    )

    // 2. Outer Top-Left Highlight
    frameworkPaint.color = highlightColor.toArgb()
    frameworkPaint.setShadowLayer(
      shadowRadius.toPx(),
      -shadowOffset.toPx(),
      -shadowOffset.toPx(),
      highlightColor.toArgb()
    )
    canvas.drawRoundRect(
      0f, 0f, size.width, size.height,
      borderRadius.toPx(), borderRadius.toPx(),
      paint
    )

    // Reset shadow layer for inner shadows
    frameworkPaint.clearShadowLayer()

    // 3. Inner Rim Light (Simulated via light inset stroke)
    paint.color = Color.White.copy(alpha = 0.5f)
    paint.style = androidx.compose.ui.graphics.PaintingStyle.Stroke
    paint.strokeWidth = 2.dp.toPx()
    canvas.drawRoundRect(
      1.dp.toPx(), 1.dp.toPx(), size.width - 1.dp.toPx(), size.height - 1.dp.toPx(),
      borderRadius.toPx(), borderRadius.toPx(),
      paint
    )

    // 4. Inner Colored Bounce Light (Subtle bottom-right inner glow)
    paint.color = shadowColor.copy(alpha = 0.05f)
    paint.style = androidx.compose.ui.graphics.PaintingStyle.Fill
    canvas.drawRoundRect(
      shadowOffset.toPx(), shadowOffset.toPx(), size.width, size.height,
      borderRadius.toPx(), borderRadius.toPx(),
      paint
    )
  }
}

/**
 * Convex button style for Claymorphism.
 */
fun Modifier.clayButton(
  borderRadius: Dp = 20.dp,
  elevation: Dp = 8.dp,
  primaryColor: Color = clayPrimary
) = this.clayShadow(
  borderRadius = borderRadius,
  elevation = elevation,
  shadowColor = primaryColor.copy(alpha = 0.3f),
  highlightColor = Color.White.copy(alpha = 0.4f)
)

/**
 * Press/touch animation for clay elements.
 * Scales down slightly on press with a bouncy spring, returns to 1.0 on release.
 */
fun Modifier.clayPressEffect(): Modifier = composed {
  var isPressed by remember { mutableStateOf(false) }
  val scale by animateFloatAsState(
    targetValue = if (isPressed) 0.96f else 1f,
    animationSpec = spring(dampingRatio = 0.6f, stiffness = 400f),
    label = "clayPressScale"
  )
  this
    .graphicsLayer { scaleX = scale; scaleY = scale }
    .pointerInput(Unit) {
      awaitPointerEventScope {
        while (true) {
          awaitFirstDown(requireUnconsumed = false)
          isPressed = true
          waitForUpOrCancellation()
          isPressed = false
        }
      }
    }
}
