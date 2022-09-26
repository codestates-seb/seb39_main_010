import 'styled-components';

declare module 'styled-components' {
	export interface DefaultTheme {
		colors: {
			blueMain: string;
			blue100: string;

			gray100: string;
			gray200: string;
			gray300: string;
			gray500: string;
			gray700: string;
			gray800: string;
			gray900: string;

			green100: string;
		};
	}
}
