/*
 * Copyright 2019 Netflix, Inc.
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

package io.mantisrx.server.worker.jobmaster;

import java.util.Properties;

public interface JobAutoscalerManager {

    JobAutoscalerManager DEFAULT = new NoopJobAutoscalerManager();

    /**
     * Knobs to toggle autoscaler scale ups
     */
    default boolean isScaleUpEnabled() {
        return true;
    }

    /**
     * Knobs to toggle autoscaler scale downs
     */
    default boolean isScaleDownEnabled() {
        return true;
    }

    /**
     * Get the current fractional value to set size for stage numWorkers.
     * Valid values are [0.0, 100.0] which set numWorkers from [min, max].
     * All other values are ignored for scaling decisions.
     */
    default double getCurrentValue() {
        return -1.0;
    }

    /**
     * Noop implementation of {@link JobAutoscalerManager} that always returns true
     * for isScaleUpEnabled, isScaleDownEnabled
     */
    class NoopJobAutoscalerManager implements JobAutoscalerManager {

        private NoopJobAutoscalerManager() {
        }

        @SuppressWarnings("unused")
        public static JobAutoscalerManager valueOf(Properties properties) {
            return DEFAULT;
        }
    }
}
