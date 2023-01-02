import { BasicButton, Input, JobSelect } from 'components/common';
import TextArea from 'components/common/Textarea/Textarea';
import React, { useRef, useState } from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import Carrer from './Carrer';

const ContentsRContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 1200px;
	margin: 44px 0;
	font-size: 18px;
	.job {
		display: flex;
		justify-content: space-between;
		margin-bottom: 40px;
	}
	.job div {
		width: 380px;
	}

	.text {
		display: flex;
		justify-content: space-between;
		margin-bottom: 14px;
	}
	.gray {
		color: ${theme.colors.gray400};
	}

	textarea {
		margin-top: 26px;
	}

	button {
		width: 500px;
	}
`;

const ContentsR = () => {
	const CARRER = [
		{ value: 'new', name: '신입' },
		{ value: 'old', name: '경력' },
	];

	const DATE = [
		{ value: 'new', name: '2022년 하반기' },
		{ value: 'new', name: '2022년 상반기' },
		{ value: 'new', name: '2021년 하반기' },
		{ value: 'new', name: '2021년 상반기' },
		{ value: 'new', name: '2020년 하반기' },
		{ value: 'new', name: '2020년 상반기' },
		{ value: 'new', name: '2019년 하반기' },
		{ value: 'new', name: '2019년 상반기' },
		{ value: 'new', name: '2018년 하반기' },
		{ value: 'new', name: '2018년 상반기' },
	];

	const titleRef = useRef<HTMLInputElement>(null);
	const textRef = useRef<HTMLTextAreaElement>(null);
	const [, setCategoryId] = useState<number>();

	function handleSubmitClick() {
		const title = titleRef.current?.value; // 값 잘 받아짐
		const text = textRef.current?.value; // 값 잘 받아짐

		return {
			title: title,
			text: text,
		};
	}
	const [countlength, setCountlength] = useState(0);

	const handleonChange = (e: { target: { value: string } }) => {
		const long = e.target.value;
		setCountlength(long.length);
	};
	return (
		<ContentsRContainer>
			<div>
				<div className="job">
					<div>
						<div className="text">직무선택</div>
						<JobSelect setState={setCategoryId} />
					</div>
					<div>
						<div className="text">경력 여부</div>
						<Carrer options={CARRER} />
					</div>
					<div>
						<div className="text">면접 날짜</div>
						<Carrer options={DATE} />
					</div>
				</div>
				<div className="text">
					<span>회사명</span>
					<span className="gray">{countlength}/50</span>
				</div>
				<Input
					placeholder={'제목을 입력해 주세요.'}
					onChange={handleonChange}
					maxLength={50}
				/>
				<TextArea
					placeholder={`면접 후기와 관련된 내용을 자유롭게 적어주세요.`}
				/>
			</div>
			<BasicButton onClick={handleSubmitClick}>등록하기</BasicButton>
		</ContentsRContainer>
	);
};
export default ContentsR;
