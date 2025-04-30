package com.tencent.tmm.kmmgradle.utils;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.tasks.TaskContainer;

/**
 * Gradle 版本工具类
 */
public class GradleUtils {

    private static final String TAG = "GradleUtils";

    private static final int GRADLE_BIG_VERSION_SIX = 6;

    /**
     * 未知版本
     */
    public static final int UNKNOWN_VERSION = -1;


    /**
     * 获取当前运行的Gradle的大版本号
     *
     * @param gradle 当前运行的gradle
     * @return 首位版本号, 获取失败时候返回 {@link #UNKNOWN_VERSION}
     */
    private static int getGradleBigVersion(Gradle gradle) {
        String currentVersion = gradle.getGradleVersion();
        String[] result = currentVersion.split("\\.");
        if (result.length < 1) {
            return UNKNOWN_VERSION;
        } else {
            try {
                return Integer.parseInt(result[0]);
            } catch (NumberFormatException exception) {
                return UNKNOWN_VERSION;
            }
        }
    }

    /**
     * 添加Gradle任务，用于适配gradle 6.0版本add api不可用
     *
     * @return 新创建的task对象，如果已经存在则返回空
     */
    public static <T extends Task> T addTaskCompact(Project project, TaskContainer taskContainer,
            String taskName, Class<T> taskType) {
        T addedTask = null;
        if (taskContainer.findByPath(taskName) == null) {
            if (getGradleBigVersion(project.getGradle()) < GRADLE_BIG_VERSION_SIX) {
                addedTask = taskContainer.create(taskName, taskType);
            } else {
                addedTask = taskContainer.register(taskName, taskType).get();
            }
        }
        return addedTask;
    }
}
