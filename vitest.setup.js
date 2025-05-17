import { expect } from 'vitest';

/* Usamos * as matchers en lugar de default, porque jest-dom/matchers
 exporta un objeto con los matchers, no un default. */
import * as matchers from '@testing-library/jest-dom/matchers';

expect.extend(matchers);
