import React, { useEffect, useState } from 'react';
import Banner from 'components/pages/Question/Banner';
import styled from 'styled-components';
import { FilterAndSearchBar } from 'components/pages';
import QuestionCards from 'components/pages/Question/QuestionCards';
import apiClient from 'apis/apiClient';
import { Question } from 'components/pages/Question/QuestionCard';

const QuestionPage = () => {
	const [questionList, setQuestionList] = useState<Question[] | undefined>();

	const getQuestionListApi = async () => {
		try {
			const response = await await apiClient.get('/api/v1/questions');
			console.log(response);

			setQuestionList(response.data.content);

			return response;
		} catch (error) {
			console.log(error);
		}
	};

	useEffect(() => {
		getQuestionListApi();
	}, []);

	return (
		<QuestionContainer>
			<Banner />
			<ContentContainer>
				<FilterAndSearchBar
					placeholder="궁금한 내용을 찾아보세요."
					navigate="/interview/question/write"
				/>
				<QuestionCards questionList={questionList} />
			</ContentContainer>
		</QuestionContainer>
	);
};

export default QuestionPage;

const QuestionContainer = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	width: 100%;
`;

const ContentContainer = styled.div`
	max-width: 1200px;
`;
