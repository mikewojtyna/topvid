package pl.wojtyna.topvid.upload.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Upload Video REST API")
@SpringBootTest
@AutoConfigureMockMvc
class UploadVideoRestApiTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName(
        """
         given some video as multipart file and user identity parameter,
         when POST on /api/v0/videos,
         then video is uploaded
        """
    )
    // @formatter:on
    @Test
    void test() throws Exception {
        // when
        mockMvc.perform(multipart("/api/v0/videos")
                            .file("file", "some fake video content".getBytes(
                                StandardCharsets.UTF_8))
                            .param("identity", "george"))

               // then
               .andExpect(status().isCreated());
    }
}
