import React from 'react';
import styled from 'styled-components';
import Accordion from './Accordion';

const ReviewsContainer = styled.div``;

const Reviews = () => {
	return (
		<ReviewsContainer>
			<Accordion />
			<Accordion />
			<Accordion />
			<Accordion />
			<Accordion />
		</ReviewsContainer>
	);
};

export default Reviews;
