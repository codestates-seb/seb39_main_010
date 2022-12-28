import React, { useState } from 'react';
import { BasicButton, Input, Intro, JobSelect } from 'components/common';
import styled from 'styled-components';
import TextArea from 'components/common/Textarea/Textarea';
import QuestionTagSelect from 'components/pages/Question/QuestionTagSelect';
import { postQuestionApi } from 'apis/authApiClient';
import { useNavigate } from 'react-router-dom';

export interface NewQuestion {
	title: string;
	content: string;
}

export interface NewQuestionSubmitData extends NewQuestion {
	categoryId?: number;
	tagId?: number;
}

const QuestionWrite = () => {
	const [question, setQuestion] = useState<NewQuestion>({
		title: '',
		content: '',
	});
	const [categoryId, setCategoryId] = useState<number>();
	const [tagId, setTagId] = useState<number>();
	const navigate = useNavigate();

	const handleChange = (
		e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
	) => {
		setQuestion({
			...question,
			[e.target.name]: e.currentTarget.value,
		});
	};

	const handleSubmit = () => {
		postQuestionApi({
			categoryId,
			tagId,
			...question,
		}).then(() => navigate('/'));
	};

	return (
		<WriteContainer>
			<InnerContainer>
				<Intro
					title={'면접 질문 작성하기'}
					content={
						'면접에서 난처했던 질문이나 고민이 되었던 질문을 같이 의견을 나눠보세요.'
					}
				/>
				<CategoryContainer>
					<div>
						<span>직무 선택</span>
						<JobSelect setState={setCategoryId} />
					</div>
					<div>
						<span>질문 유형</span>
						<QuestionTagSelect setState={setTagId} />
					</div>
				</CategoryContainer>
				<Input
					value={question.title}
					placeholder="제목을 입력해 주세요."
					maxLength={50}
					label="면접 질문"
					className="title"
					name="title"
					onChange={handleChange}
				/>
				<TextArea
					value={question.content}
					placeholder={`면접 후기와 관련된 내용을 자유롭게 적어주세요.`}
					name="content"
					onChange={handleChange}
				/>
				<BasicButton onClick={handleSubmit}>등록하기</BasicButton>
			</InnerContainer>
		</WriteContainer>
	);
};

export default QuestionWrite;

const WriteContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

const InnerContainer = styled.div`
	max-width: 1200px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	.title {
		margin-bottom: 26px;
	}

	button {
		width: 500px;
		margin-top: 44px;
	}
`;

const CategoryContainer = styled.div`
	width: 100%;
	display: flex;
	gap: 48px;
	margin: 44px 0;

	div {
		width: 100%;
	}

	span {
		display: inline-block;
		font-size: 18px;
		margin-bottom: 18px;
	}
`;
