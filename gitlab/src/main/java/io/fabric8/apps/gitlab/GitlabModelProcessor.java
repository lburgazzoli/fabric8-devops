/*
 * Copyright 2005-2015 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.fabric8.apps.gitlab;

import io.fabric8.kubernetes.generator.annotation.KubernetesModelProcessor;
import io.fabric8.openshift.api.model.TemplateBuilder;

import java.util.Arrays;

@KubernetesModelProcessor
public class GitlabModelProcessor {
    
    private static final String NAME = "gitlab";

    public void onList(TemplateBuilder builder) {
        builder.addNewServiceObject()
                .withNewMetadata()
                .withName(NAME)
                .addToLabels("project", NAME)
                .addToLabels("provider", "fabric8")
                .endMetadata()
                .withNewSpec()
                .withType("LoadBalancer")
                .addNewPort()
                .withProtocol("TCP")
                .withPort(80)
                .withNewTargetPort(80)
                .endPort()
                .addToSelector("project", NAME)
                .addToSelector("provider", "fabric8")
                .endSpec()
                .endServiceObject()

                // Second service
                .addNewServiceObject()
                .withNewMetadata()
                .withName(NAME + "-ssh")
                .addToLabels("project", NAME)
                .addToLabels("provider", "fabric8")
                .endMetadata()
                .withNewSpec()
                .addNewPort()
                .withProtocol("TCP")
                .withPort(22)
                .withNewTargetPort(22)
                .endPort()
                .addToSelector("project", NAME)
                .addToSelector("provider", "fabric8")
                .endSpec()
                .endServiceObject()
                .build();
    }
}
