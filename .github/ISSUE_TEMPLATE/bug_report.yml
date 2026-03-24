name: "🐛 Bug Report"
description: "버그를 발견했을 때 사용하는 템플릿입니다."
title: "[Bug] "
labels: ["🐛 bug"]
assignees: []

body:
  - type: markdown
    attributes:
      value: |
        ## 버그 리포트
        > 버그를 최대한 상세히 기록해주세요. 재현 가능한 정보가 많을수록 빠르게 해결할 수 있습니다.

  - type: textarea
    id: description
    attributes:
      label: "📋 버그 설명"
      description: "어떤 버그인지 간결하고 명확하게 설명해주세요."
      placeholder: "예: 로그인 버튼 클릭 시 페이지가 새로고침되며 로그인이 되지 않습니다."
    validations:
      required: true

  - type: textarea
    id: steps
    attributes:
      label: "🔁 재현 방법"
      description: "버그를 재현하는 단계를 순서대로 작성해주세요."
      placeholder: |
        1. '...' 페이지로 이동
        2. '...' 버튼 클릭
        3. '...' 입력 후 제출
        4. 에러 발생 확인
    validations:
      required: true

  - type: textarea
    id: expected
    attributes:
      label: "✅ 기대 동작"
      description: "정상적으로 동작했을 때 어떤 결과가 나와야 하는지 설명해주세요."
      placeholder: "예: 로그인 성공 후 메인 페이지로 리다이렉트되어야 합니다."
    validations:
      required: true

  - type: textarea
    id: actual
    attributes:
      label: "❌ 실제 동작"
      description: "현재 실제로 발생하는 결과를 설명해주세요."
      placeholder: "예: 페이지가 새로고침되며 로그인 폼이 초기화됩니다."
    validations:
      required: true

  - type: textarea
    id: screenshots
    attributes:
      label: "📸 스크린샷 / 로그"
      description: "관련 스크린샷이나 에러 로그가 있다면 첨부해주세요."
      placeholder: "이미지를 드래그 앤 드롭하거나, 에러 로그를 붙여넣기 해주세요."
    validations:
      required: false

  - type: dropdown
    id: severity
    attributes:
      label: "🔥 심각도"
      description: "버그의 심각도를 선택해주세요."
      options:
        - "Critical - 서비스 이용 불가"
        - "Major - 핵심 기능 장애"
        - "Minor - 사소한 기능 오류"
        - "Low - UI/UX 개선 사항"
    validations:
      required: true

  - type: textarea
    id: environment
    attributes:
      label: "🖥️ 환경 정보"
      description: "버그가 발생한 환경을 기입해주세요."
      placeholder: |
        - OS: Windows 11 / macOS Sonoma
        - Browser: Chrome 120
        - Version: v1.0.0
    validations:
      required: false

  - type: checkboxes
    id: checklist
    attributes:
      label: "✔️ 체크리스트"
      description: "제출 전 확인해주세요."
      options:
        - label: "동일한 버그가 이미 등록되어 있는지 확인했습니다."
          required: true
        - label: "최신 버전에서 테스트했습니다."
          required: false
