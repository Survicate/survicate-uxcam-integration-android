package com.survicate.uxcam.integration

import com.survicate.surveys.QuestionAnsweredEvent
import com.survicate.surveys.SurvicateAnswer
import com.survicate.surveys.surveys.CtaSurveyAnswerType
import com.survicate.surveys.surveys.QuestionSurveyAnswerType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SurvicateUXCamIntegrationKtTest {

    @Test
    fun `has correct provider name`() {
        assertEquals("uxcam", SurvicateUXCamIntegration().providerName)
    }

    @Test
    fun `creates UXCam event properties from Survicate question answered event`() {
        val event = QuestionAnsweredEvent(
            responseUuid = SAMPLE_RESPONSE_UUID,
            visitorUuid = SAMPLE_VISITOR_UUID,
            surveyId = "testSurveyId",
            surveyName = "Test Survey",
            questionId = 1,
            questionText = "Question text",
            answer = SurvicateAnswer(
                type = QuestionSurveyAnswerType.SINGLE,
                id = 2,
                ids = null,
                value = "Test answer",
            ),
            panelAnswerUrl = "https://test.com"
        )

        val uxCamProps = event.toUxCamEventProperties()

        assertEquals("Test answer", uxCamProps["answer"])
        assertEquals("Question text", uxCamProps["question"])
        assertEquals(QuestionSurveyAnswerType.SINGLE, uxCamProps["question_type"])
        assertEquals(SAMPLE_RESPONSE_UUID, uxCamProps["response_uuid"])
        assertEquals(SAMPLE_VISITOR_UUID, uxCamProps["visitor_uuid"])
        assertEquals("testSurveyId", uxCamProps["survey_id"])
        assertEquals("Test Survey", uxCamProps["survey_name"])
        assertEquals("https://test.com", uxCamProps["response_url"])
    }

    @Test
    fun `creates UXCam event properties with empty string as answer if SurvicateAnswer value is null`() {
        val event = QuestionAnsweredEvent(
            responseUuid = SAMPLE_RESPONSE_UUID,
            visitorUuid = SAMPLE_VISITOR_UUID,
            surveyId = "testSurveyId",
            surveyName = "Test Survey",
            questionId = 1,
            questionText = "Lorem ipsum",
            answer = SurvicateAnswer(
                type = CtaSurveyAnswerType.BUTTON_CLOSE,
                id = null,
                ids = null,
                value = null,
            ),
            panelAnswerUrl = "https://test.com"
        )

        val uxCamProps = event.toUxCamEventProperties()

        assertEquals("", uxCamProps["answer"])
    }

    companion object {
        private const val SAMPLE_RESPONSE_UUID = "00000000-0000-0000-0000-000000000000"
        private const val SAMPLE_VISITOR_UUID = "10000000-0000-0000-0000-000000000001"
    }
}
