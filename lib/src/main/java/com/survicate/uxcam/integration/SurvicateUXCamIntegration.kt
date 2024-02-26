package com.survicate.uxcam.integration

import com.survicate.surveys.IntegrationListener
import com.survicate.surveys.IntegrationPayload
import com.survicate.surveys.QuestionAnsweredEvent
import com.uxcam.UXCam

class SurvicateUXCamIntegration : IntegrationListener() {

    override val providerName: String = "uxcam"

    override fun onWillSendAnswer(): Map<String, IntegrationPayload> {
        val sessionUrl = UXCam.urlForCurrentSession()
        return mapOf("uxcam_url" to IntegrationPayload(sessionUrl))
    }

    override fun onQuestionAnswered(event: QuestionAnsweredEvent) {
        val eventArgs = event.toUxCamEventProperties()
        UXCam.logEvent("survicate.Questionanswered", eventArgs)
    }
}

internal fun QuestionAnsweredEvent.toUxCamEventProperties(): Map<String, Any> {
    return mapOf(
        "answer" to answer.value.orEmpty(),
        "question" to questionText,
        "question_type" to answer.type,
        "response_uuid" to responseUuid,
        "visitor_uuid" to visitorUuid,
        "survey_id" to surveyId,
        "survey_name" to surveyName,
        "response_url" to panelAnswerUrl
    )
}
