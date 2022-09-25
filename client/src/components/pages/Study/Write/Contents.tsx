import { Input, SortingRectangle } from 'components/common';
import TextArea from 'components/common/Textarea/Textarea';
import React from 'react';
import styled from 'styled-components';

const ContentsContainer = styled.div`
	width: 1200px;
`;
const JobandTag = styled.div`
	display: flex;
`;

const Contents = () => {
	const OPTIONS = [
		{ value: 'all', name: '직무전체' },
		{ value: 'sales', name: '영업/고객상담' },
		{ value: 'business', name: '경영/사무' },
		{ value: 'marketing', name: '마케팅/광고/홍보' },
		{ value: 'produce', name: '생산/제조' },
		{ value: 'research', name: '연구개발/설계' },
		{ value: 'internet', name: 'IT/인터넷' },
		{ value: 'service', name: '서비스' },
		{ value: 'trade', name: '무역/유통' },
		{ value: 'medical', name: '의료' },
		{ value: 'structure', name: '건설' },
		{ value: 'education', name: '교육' },
		{ value: 'design', name: '디자인' },
		{ value: 'profession', name: '전문/특수직' },
		{ value: 'media', name: '미디어' },
	];
	return (
		<ContentsContainer>
			<JobandTag>
				<div>
					<div>직무선택</div>
					<SortingRectangle options={OPTIONS} />
				</div>
				<div>
					<div>
						<span>관련태그</span>
						<span>띄어쓰기로 구분해 주세요</span>
					</div>
					<div>
						<Input
							width="500px"
							height="66px"
							placeholder={'태그를 설정해 주세요 (최대 10개)'}
						/>
					</div>
				</div>
			</JobandTag>
			<div>
				<div>글제목</div>
				<Input
					width="1200px"
					height="66px"
					placeholder={'제목을 입력해 주세요.'}
				/>
				<TextArea
					placeholder={`스터디 모집글을 아래 양식을 참고해 작성해주세요.
꼼꼼히 작성하면 멋진 스터디 팀원을 만나실 수 있을거예요.

[스터디 모집 내용 예시]

스터디 주제 :

스터디 목표 :

예상 스터디 일정(횟수) :

예상 커리큘럼 간략히 :

예상 모집인원 :

스터디 소개와 개설 이유 :

스터디 관련 주의사항 :

스터디에 지원할 수 있는 방법을 남겨주세요. (이메일, 카카오 오픈채팅방, 구글폼 등.)`}
				/>
			</div>
		</ContentsContainer>
	);
};
export default Contents;
