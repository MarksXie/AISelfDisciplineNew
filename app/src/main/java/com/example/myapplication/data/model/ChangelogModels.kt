package com.example.myapplication.data.model

/**
 * 更新条目类型
 */
enum class ChangeType(val label: String) {
    FEAT("新增"),
    FIX("修复"),
    OPTIMIZE("优化")
}

/**
 * 单条更新说明
 */
data class ChangeItem(
    val type: ChangeType,
    val description: String
)

/**
 * 单个版本的发布日志模型
 */
data class ReleaseLog(
    val versionName: String,
    val versionCode: Int,
    val releaseDate: String,
    val isLatest: Boolean = false,
    val title: String,
    val changes: List<ChangeItem>
)

/**
 * 内置历史版本变更记录数据源
 */
object AppChangelogRepository {

    val releases: List<ReleaseLog> = listOf(
        ReleaseLog(
            versionName = "1.4",
            versionCode = 5,
            releaseDate = "2026.09.01",
            isLatest = true,
            title = "自律复盘报告隔离与版本日志模块",
            changes = listOf(
                ChangeItem(
                    type = ChangeType.FIX,
                    description = "修复历史翻页与跨周期查看时复盘报告未独立加载的问题，实现各历史周期的报告严格隔离与即时缓存呈现。"
                ),
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "在 UI 端「关于」模块中集成交互式「版本更新日志」对话框，支持查看历史演进与改动明细。"
                ),
                ChangeItem(
                    type = ChangeType.OPTIMIZE,
                    description = "屏幕总活跃时长与应用使用排行支持随选定日期/周期（日/周/月/年）及翻页动态异步联动刷新。"
                ),
                ChangeItem(
                    type = ChangeType.OPTIMIZE,
                    description = "优化统计 ViewModel 的状态快照同步机制，提升日期与周期切换时的交互流畅度。"
                )
            )
        ),
        ReleaseLog(
            versionName = "1.3",
            versionCode = 4,
            releaseDate = "2026.08.28",
            isLatest = false,
            title = "多周期 AI 自律统计复盘与分层级联分析",
            changes = listOf(
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "新增日、周、月、季、半年、年 6 大自律统计分析周期。"
                ),
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "引入分层级联 AI 评价机制（周报汇总日报、年报汇总季报），提升长周期宏观分析质量。"
                ),
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "新增 WorkManager 后台定时复盘任务与统计专用云端模型配置。"
                ),
                ChangeItem(
                    type = ChangeType.OPTIMIZE,
                    description = "重构统计页面为 Warm Light & Terracotta 陶土奶咖温润设计风格。"
                )
            )
        ),
        ReleaseLog(
            versionName = "1.2",
            versionCode = 3,
            releaseDate = "2026.08.15",
            isLatest = false,
            title = "端云双引擎与全屏沉浸式审查交互",
            changes = listOf(
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "集成 C++20 / llama.cpp 端侧离线大模型推理引擎与 OpenAI/DeepSeek 兼容云端双引擎。"
                ),
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "支持自定义审查官人设（冷酷教官、知性导师等）及多轮深度追问机制。"
                ),
                ChangeItem(
                    type = ChangeType.OPTIMIZE,
                    description = "优化刻度转盘使用时长选择器与应用专属规则定制系统。"
                )
            )
        ),
        ReleaseLog(
            versionName = "1.1",
            versionCode = 2,
            releaseDate = "2026.08.01",
            isLatest = false,
            title = "前台保活与无障碍拦截强化",
            changes = listOf(
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "新增 Android 14 规范前台保活常驻服务与开机自启引导。"
                ),
                ChangeItem(
                    type = ChangeType.FIX,
                    description = "解决部分定制系统下无障碍悬浮窗偶发层级覆盖失效的问题。"
                )
            )
        ),
        ReleaseLog(
            versionName = "1.0",
            versionCode = 1,
            releaseDate = "2026.07.20",
            isLatest = false,
            title = "初始版本发布",
            changes = listOf(
                ChangeItem(
                    type = ChangeType.FEAT,
                    description = "AI 锁机自律基础架构上线，支持黑名单应用监听与意图审批放行流程。"
                )
            )
        )
    )
}
