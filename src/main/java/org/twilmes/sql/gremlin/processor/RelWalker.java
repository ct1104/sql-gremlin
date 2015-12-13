/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.twilmes.sql.gremlin.processor;

import org.apache.calcite.rel.RelNode;

/**
 * Does a post-order depth-first traversal of the Calcite operator tree.
 */
public class RelWalker {

    private final RelVisitor visitor;

    public RelWalker(RelNode root, RelVisitor visitor) {
        this.visitor = visitor;
        walk(root);
    }

    private void walk(RelNode node) {
        if (node == null) return;

        for (RelNode child : node.getInputs()) {
            walk(child);
        }

        visitor.visit(node);
    }
}