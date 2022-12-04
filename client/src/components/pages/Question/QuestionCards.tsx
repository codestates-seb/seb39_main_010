import React from 'react';
import styled from 'styled-components';
import QuestionCard, { Question } from './QuestionCard';

interface Prop {
	questionList?: Question[];
}

const QuestionCards = ({ questionList }: Prop) => {
	if (!questionList) return <div>Loading...</div>;
	return (
		<CardsContainer>
			{questionList.map((el) => (
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
