import React from 'react';
import styled from 'styled-components';
import CardDone from '../Card/CardDone';

export const CardContentDoneContainer = styled.div`
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	grid-auto-rows: minmax(272px, auto);
	grid-gap: 20px;
	margin-top: 1.5rem;

	width: 1200px;
	background-color: yellow;
`;

const CardContentAll = () => {
	return (
		<CardContentDoneContainer>
			<CardDone />
		</CardContentDoneContainer>
	);
};
export default CardContentAll;
