
package com.tencent.tmm.kmmresource.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tencent.tmm.kmmresource.resource.ColorResource

@Composable
expect fun colorResource(resource: ColorResource): Color
