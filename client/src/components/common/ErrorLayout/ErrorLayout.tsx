import React from 'react';
import { RiChatDeleteLine } from 'react-icons/ri';
import styled from 'styled-components';
import { theme } from 'styles/theme';

const ErrorLayout = ({ children }: { children: React.ReactNode }) => {
	return (
		<Container>
			<NotFoundBox>
				<RiChatDeleteLine size={48} />
				{children}
			</NotFoundBox>
		</Container>
	);
};

export default ErrorLayout;

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
