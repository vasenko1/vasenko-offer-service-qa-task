export function clickOutside() {
    cy.get('body').click(0, 0);
}
