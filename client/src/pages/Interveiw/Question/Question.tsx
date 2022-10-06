import React, { useEffect } from 'react';
import Banner from 'components/pages/Question/Banner';
import styled from 'styled-components';
import { SearchBar } from 'components/pages';
import QuestionCards from 'components/pages/Question/QuestionCards';
import apiClient from 'apis/apiClient';

const Question = () => {
	const getQuestions = async () => {
		try {
			const response = await apiClient.get('/api/v1/questions');
			console.log(response);
		} catch (error) {
			console.log(error);
		}
	};

	useEffect(() => {
		getQuestions();
	}, []);

	return (
		<QuestionContainer>
			<Banner />
			<ContentContainer>
				<SearchBar
					placeholder="궁금한 내용을 찾아보세요."
					navigate="/interview/question/write"
				/>
				<QuestionCards />
			</ContentContainer>
		</QuestionContainer>
	);
};

export default Question;

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
