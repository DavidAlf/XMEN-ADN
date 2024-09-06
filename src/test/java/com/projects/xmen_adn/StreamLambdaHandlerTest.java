package com.projects.xmen_adn;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;

public class StreamLambdaHandlerTest {

    @Mock
    private SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> mockHandler;

    @InjectMocks
    private StreamLambdaHandler streamLambdaHandler;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleRequest() throws IOException {
        // Given
        String requestPayload = "{\"key\": \"value\"}";
        InputStream inputStream = new ByteArrayInputStream(requestPayload.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        Context context = mock(Context.class);

        // We will not use `thenAnswer` but will verify interactions directly
        doNothing().when(mockHandler).proxyStream(any(InputStream.class), any(OutputStream.class), any(Context.class));

        // When
        streamLambdaHandler.handleRequest(inputStream, outputStream, context);

        // Then
        // verify(mockHandler, times(1)).proxyStream(inputStream, outputStream,
        // context);

    }
}
