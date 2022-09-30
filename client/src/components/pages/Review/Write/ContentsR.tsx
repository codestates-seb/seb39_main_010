import { Input } from 'components/common';
import JobSelect from 'components/common/SelectBox/JobSelect';
import TextArea from 'components/common/Textarea/Textarea';
// import { ContentsContainer } from 'components/pages/Study/Write/ContentsS';
import React from 'react';
import styled from 'styled-components';
import { theme } from 'styles/theme';
import Carrer from './Carrer';

const ContentsRContainer = styled.div`
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
`;

const ContentsR = () => {
	const OPTIONS = [
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

	return (
		<ContentsRContainer>
			<div>
				<div className="job">
					<div>
						<div className="text">직무선택</div>
						<JobSelect options={OPTIONS} />
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
					<span className="gray">0/50</span>
				</div>
				<Input placeholder={'제목을 입력해 주세요.'} />
				<TextArea
					placeholder={`면접 후기와 관련된 내용을 자유롭게 적어주세요.`}
				/>
			</div>
		</ContentsRContainer>
	);
};
export default ContentsR;
