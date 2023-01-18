import React from 'react';
import { RiChatDeleteLine } from 'react-icons/ri';
import styled from 'styled-components';
import { theme } from 'styles/theme';

const NotFound = () => {
	return (
		<Container>
			<NotFoundBox>
				<RiChatDeleteLine size={48} />
				<div>죄송합니다. 해당 페이지를 찾을 수 없습니다.</div>
				<div>
					<span>404</span> NOT FOUND
				</div>
			</NotFoundBox>
		</Container>
	);
};

export default NotFound;

const Container = styled.div`
	width: 100%;
	height: 80vh;
	display: flex;
	justify-content: center;
	align-items: center;
`;

const NotFoundBox = styled.div`
	display: flex;
	flex-direction: column;
	gap: 0.5rem;
	border: 4px solid ${theme.colors.blueMain};
	padding: 2rem;
	border-radius: 1rem;
	font-size: 1.5rem;
	font-weight: bold;
	color: ${theme.colors.gray800};

	svg {
		color: ${theme.colors.blueMain};
	}

	span {
		color: ${theme.colors.blueMain};
	}
`;
