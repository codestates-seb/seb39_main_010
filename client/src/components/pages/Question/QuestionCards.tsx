import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { questionDummy } from '../../../assets/data/questionDummy';
import QuestionCard from './QuestionCard';

const QuestionCards = () => {
	return (
		<CardsContainer>
			{questionDummy.map((el) => (
				<QuestionCard key={el.questionId} data={el} />
			))}
		</CardsContainer>
	);
};

export default QuestionCards;

const CardsContainer = styled.div`
	width: 100%;

	display: grid;
	grid-template-columns: repeat(2, 1fr);
	grid-gap: 20px;
	margin-top: 1.5rem;
`;
