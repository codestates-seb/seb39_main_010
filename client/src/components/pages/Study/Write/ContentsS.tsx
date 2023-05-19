import { Input, JobSelect } from 'components/common';
import TextArea from 'components/common/Textarea/Textarea';
import React, { useState } from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import { Tag } from './Tag';

export const ContentsContainer = styled.div`
	width: 1200px;
	margin: 44px 0;
	font-size: 18px;
	.text {
		display: flex;
		justify-content: space-between;
		margin-bottom: 14px;
	}
	.blank {
		margin-bottom: 44px;
	}
	.gray {
		color: ${theme.colors.gray400};
	}

	textarea {
		margin-top: 26px;
	}
	> div:nth-child(2) {
		margin-top: 35px;
	}
`;
const JobandTag = styled.div`
	display: flex;
	justify-content: space-between;
	input {
		width: 576px;
	}

	div {
	}
`;

const ContentsS = () => {
	const [countlength, setCountlength] = useState(0);
	const [, setCategoryId] = useState<number>();

	const handleonChange = (e: { target: { value: string } }) => {
		const long = e.target.value;
		setCountlength(long.length);
	};

	return (
		<ContentsContainer>
			<JobandTag>
				<div>
					<div className="text">직무선택</div>
					<JobSelect setState={setCategoryId} />
				</div>
				<div>
					<div className="text">
						<span>관련태그</span>
						<span className="gray">엔터키로 구분해 주세요</span>
					</div>
					<div>
						{/* <Input
							className="blank"
							width="576px"
							height="66px"
							placeholder={'태그를 설정해 주세요 (최대 10개)'}
						/> */}
						<Tag />
					</div>
				</div>
			</JobandTag>
			<div>
				<div className="text">
					<span>글제목</span>
					<span className="gray">{countlength}/50</span>
				</div>
				<Input
					placeholder={'제목을 입력해 주세요.'}
					onChange={handleonChange}
					maxLength={50}
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
export default ContentsS;
