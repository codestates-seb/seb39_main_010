import React from 'react';
import styled from 'styled-components';
import Card from './Card';

export const CardContentContainer = styled.div`
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	grid-auto-rows: minmax(272px, auto);
	grid-gap: 20px;
	margin-top: 1.5rem;

	width: 1200px;
	background-color: yellow;
`;

const CardContent = () => {
	return (
		<CardContentContainer>
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
			<Card />
		</CardContentContainer>
	);
};
export default CardContent;
