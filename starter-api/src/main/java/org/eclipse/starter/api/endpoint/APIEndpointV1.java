/********************************************************************************
 * Copyright (c) 2020 Jeyvison Nascimento and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Jeyvison Nascimento - initial API and implementation
 ********************************************************************************/

package org.eclipse.starter.api.endpoint;


import org.eclipse.starter.api.dto.ProjectDTO;
import org.eclipse.starter.core.service.StarterService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("v1")
public class APIEndpointV1 {

    @Inject
    private StarterService starterService;

    @POST
    @Produces("application/zip")
    public Response createArchive(ProjectDTO projectDTO) {

        byte[] archive = starterService.generateArchive(
                projectDTO.getArtifactId(),
                projectDTO.getGroupId(),
                projectDTO.getPackageName(),
                projectDTO.getProjectName(),
                projectDTO.getSpecifications()
        );

        return Response
                .ok()
                .header("Content-Length", archive.length)
                .header("Content-Disposition", "attachment; filename=\"" + "test" + "\"")
                .type("application/zip")
                .entity(archive)
                .build();

    }





}
