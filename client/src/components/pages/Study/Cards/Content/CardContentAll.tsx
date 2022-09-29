import React from 'react';
import styled from 'styled-components';
import CardAll from '../Card/CardAll';

export const CardContentContainer = styled.div`
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	grid-auto-rows: minmax(272px, auto);
	grid-gap: 20px;
	margin-top: 1.5rem;

	width: 1200px;
`;

const CardContentAll = () => {
	return (
		<CardContentContainer>
			<CardAll />
		</CardContentContainer>
	);
};
export default CardContentAll;
