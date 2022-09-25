import React, { useState } from 'react';
import styled from 'styled-components';
import { Intro } from 'components/common';
import { Category } from 'components/pages';

export const StudyContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
`;

export interface IOptions {
	value: string;
	name: string;
}
const Study = () => {
	const [data, setData] = useState('initial data');

	return (
		<StudyContainer>
			<Intro
				title={`스터디 모집`}
				content={`함께 성장할 스터디를 모집해 보세요.`}
			/>
			<Category />
		</StudyContainer>
	);
};

export default Study;
