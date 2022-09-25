import React, { useState } from 'react';
import styled from 'styled-components';
import CardOn from '../Card/CardOn';

export const CardContentOnContainer = styled.div`
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	grid-auto-rows: minmax(272px, auto);
	grid-gap: 20px;
	margin-top: 1.5rem;

	width: 1200px;
	background-color: yellow;
`;

const CardContentOn = () => {
	const [recruit, setRecruit] = useState(true);

	return (
		<CardContentOnContainer>
			<CardOn />
		</CardContentOnContainer>
	);
};
export default CardContentOn;
