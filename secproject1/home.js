let lightStatus = false;
let fanStatus = false;
let thermostatStatus = null;

function toggleLight() {
    lightStatus = !lightStatus;
    const lightElement = document.getElementById('lightStatus');
    if (lightStatus) {
        lightElement.textContent = "The light is ON.";
    } else {
        lightElement.textContent = "The light is OFF.";
    }
}

function toggleFan() {
    fanStatus = !fanStatus;
    const fanElement = document.getElementById('fanStatus');
    if (fanStatus) {
        fanElement.textContent = "The fan is ON.";
    } else {
        fanElement.textContent = "The fan is OFF.";
    }
}

function setTemperature() {
    const tempInput = document.getElementById('temperatureInput').value;
    if (tempInput && tempInput >= 0 && tempInput <= 50) {
        thermostatStatus = tempInput;
        const thermostatElement = document.getElementById('thermostatStatus');
        thermostatElement.textContent = `Thermostat set to ${thermostatStatus}°C.`;
    } else {
        alert("Please enter a valid temperature between 0°C and 50°C.");
    }
}
