package com.example.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.model.AppChangelogRepository
import com.example.myapplication.data.model.ChangeItem
import com.example.myapplication.data.model.ChangeType
import com.example.myapplication.data.model.ReleaseLog
import com.example.myapplication.ui.theme.AmberWarm
import com.example.myapplication.ui.theme.AmberWarmContainer
import com.example.myapplication.ui.theme.CoralDanger
import com.example.myapplication.ui.theme.CoralDangerContainer
import com.example.myapplication.ui.theme.OnAmberWarmContainer
import com.example.myapplication.ui.theme.OnCoralDangerContainer
import com.example.myapplication.ui.theme.OnSageGreenContainer
import com.example.myapplication.ui.theme.SageGreen
import com.example.myapplication.ui.theme.SageGreenContainer
import com.example.myapplication.ui.theme.TerracottaPrimary
import com.example.myapplication.ui.theme.TerracottaPrimaryContainer
import com.example.myapplication.ui.theme.WarmBorder
import com.example.myapplication.ui.theme.WarmBorderLight
import com.example.myapplication.ui.theme.WarmSurface
import com.example.myapplication.ui.theme.WarmSurfaceContainer
import com.example.myapplication.ui.theme.WarmTextMuted
import com.example.myapplication.ui.theme.WarmTextPrimary
import com.example.myapplication.ui.theme.WarmTextSecondary

@Composable
fun ChangelogDialog(onDismiss: () -> Unit) {
    val releases = AppChangelogRepository.releases

    AlertDialog(
        onDismissRequest = onDismiss,
        title = null,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                // 顶部标题栏
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = TerracottaPrimaryContainer,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Rounded.History,
                                    contentDescription = null,
                                    tint = TerracottaPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                        Column {
                            Text(
                                text = "版本演进与更新日志",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = WarmTextPrimary
                            )
                            Text(
                                text = "AISelfDiscipline Release Notes",
                                fontSize = 11.sp,
                                color = WarmTextMuted
                            )
                        }
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "关闭",
                            tint = WarmTextMuted,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = WarmBorderLight, thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

                // 版本列表滚动区
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 420.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    itemsIndexed(releases) { index, release ->
                        ReleaseCard(release = release)
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TerracottaPrimary,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "关闭",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        },
        containerColor = WarmSurface,
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
private fun ReleaseCard(release: ReleaseLog) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (release.isLatest) WarmSurface else WarmSurfaceContainer,
        border = BorderStroke(
            if (release.isLatest) 1.2.dp else 0.8.dp,
            if (release.isLatest) TerracottaPrimary else WarmBorder
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 头部：版本号 + 标签 + 日期
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "v${release.versionName}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (release.isLatest) TerracottaPrimary else WarmTextPrimary
                    )
                    if (release.isLatest) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = TerracottaPrimaryContainer
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.AutoAwesome,
                                    contentDescription = null,
                                    tint = TerracottaPrimary,
                                    modifier = Modifier.size(10.dp)
                                )
                                Text(
                                    text = "最新版本",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaPrimary
                                )
                            }
                        }
                    }
                }
                Text(
                    text = release.releaseDate,
                    fontSize = 11.sp,
                    color = WarmTextMuted
                )
            }

            // 版本主标题
            Text(
                text = release.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = WarmTextSecondary
            )

            // 具体改动明细列表
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                release.changes.forEach { item ->
                    ChangeItemRow(item = item)
                }
            }
        }
    }
}

@Composable
private fun ChangeItemRow(item: ChangeItem) {
    val (badgeBg, badgeText, badgeLabel) = when (item.type) {
        ChangeType.FEAT -> Triple(SageGreenContainer, OnSageGreenContainer, "新增")
        ChangeType.FIX -> Triple(CoralDangerContainer, OnCoralDangerContainer, "修复")
        ChangeType.OPTIMIZE -> Triple(AmberWarmContainer, OnAmberWarmContainer, "优化")
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = badgeBg,
            modifier = Modifier.padding(top = 1.dp)
        ) {
            Text(
                text = badgeLabel,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = badgeText,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = item.description,
            fontSize = 11.5.sp,
            color = WarmTextPrimary,
            lineHeight = 16.sp,
            modifier = Modifier.weight(1f)
        )
    }
}
